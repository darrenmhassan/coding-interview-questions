import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Example Input
 * Product category to category term: [
 * ["office", "printer paper"],
 * ["office", "computer desk"],
 * ["garden", "garden rake"],
 * ["garden", "picnic table"],
 * ["garden", "garden table"],
 * ["kitchen", "kitchen table"]
 * ]
 *
 * Product descriptions: [
 * "folding picnic table",
 * "merry garden kids wooden picnic bench",
 * "hp printer paper"
 * ]
 *
 * Example Output
 * Tagged product description: {
 * {"folding picnic table", "garden"},
 * {"merry garden kids wooden picnic bench", "none"},
 * {"hp printer paper", "office"}
 * }
 */
public class ProductDescriptionProcessor {

    public Map<String, String> tagProductDescriptionWithCategory(
            final List<List<String>> categoryTerms,
            final List<String> productDescriptions) {

        final Map<List<String>, String> categoryTermToCategory = new HashMap<>();
        for (final List<String> categoryAndCategoryTerm : categoryTerms) {
            final String category = categoryAndCategoryTerm.get(0);
            final String categoryTerm = categoryAndCategoryTerm.get(1);
            final String[] categoryTermArray = categoryTerm.split(" ");
            final List<String> categoryTermList = Arrays.asList(categoryTermArray);
            categoryTermToCategory.put(categoryTermList, category);
        }

        final Map<String, String> result = new HashMap<>();
        for (final String productDescription : productDescriptions) {
            final String[] prodDescWordsArray = productDescription.split(" ");
            for (int i = 0; i+1 < prodDescWordsArray.length; i++) {
                final List<String> prodDescWordPair = Arrays.asList(prodDescWordsArray[i], prodDescWordsArray[i+1]);
                if (categoryTermToCategory.containsKey(prodDescWordPair)) {
                    result.put(productDescription, categoryTermToCategory.get(prodDescWordPair));
                    break;
                }
            }
            if (!result.containsKey(productDescription)) {
                result.put(productDescription, "none");
            }
        }
        return result;
    }

    public static void main(String args[]) {
        final List<List<String>> categoryTerms = new ArrayList<>();
        categoryTerms.add(new ArrayList<>(Arrays.asList("office", "printer paper")));
        categoryTerms.add(new ArrayList<>(Arrays.asList("office", "computer desk")));
        categoryTerms.add(new ArrayList<>(Arrays.asList("garden", "garden rake")));
        categoryTerms.add(new ArrayList<>(Arrays.asList("garden", "picnic table")));
        categoryTerms.add(new ArrayList<>(Arrays.asList("garden", "garden table")));
        categoryTerms.add(new ArrayList<>(Arrays.asList("kitchen", "kitchen table")));

        final List<String> productDescriptions = new ArrayList<>();
        productDescriptions.add("folding picnic table");
        productDescriptions.add("merry garden kids wooden picnic bench");
        productDescriptions.add("hp printer paper");

        final ProductDescriptionProcessor processor = new ProductDescriptionProcessor();
        final Map<String, String> result = processor.tagProductDescriptionWithCategory(categoryTerms, productDescriptions);
        for (Map.Entry<String, String> e : result.entrySet()) {
            System.out.println(String.format("Product description: '%s' tagged with '%s'", e.getKey(), e.getValue()));
        }
    }

}
