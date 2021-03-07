import java.util.*;

/**
 * Given a log of website requests, where each request entry contains (time, customerId, page visited),
 * find the top 3-page sequence visited. For example, if we have two customers,
 * and we log CustomerA visiting page A->B->C->D->E and CustomerB visiting E->B->C->D->A,
 * then the top 3-page sequence is B->C->D.
 */
public class TopWebsiteRequests<K, V extends Comparable<V>> {

    public static <K, V extends Comparable<V> > TreeMap<K, V> valueSort(Map<K, V> map) {
        Comparator<K> valueComparator = new Comparator<K>() {
            public int compare(K k1, K k2) {
                int comp = map.get(k1).compareTo(map.get(k2));
                if (comp == 0) {
                    return 1;
                } else {
                    return comp;
                }
            }
        };
        TreeMap<K, V> sortedMap = new TreeMap<>(valueComparator);
        sortedMap.putAll(map);
        return sortedMap;
    }

    static class WebsiteRequest implements Comparable<WebsiteRequest> {
        String customerId;
        long time;
        String pageVisited;

        public int compareTo(WebsiteRequest o) {
            int customerIdComparison = this.customerId.compareTo(o.customerId);
            if (customerIdComparison == 0) {
                return Long.compare(this.time, o.time);
            }
            return customerIdComparison;
        }
    }

    public List<String> topPageSequence(List<WebsiteRequest> websiteRequests, int seqLen) {
        Collections.sort(websiteRequests);
        Map<String, Integer> sequences = new HashMap<>();
        String customerId = null;
        for (int i = 0; i <= websiteRequests.size() - seqLen; i++) {
            if (customerId == null || !customerId.equals(websiteRequests.get(i).customerId)) {
                customerId = websiteRequests.get(i).customerId;
            }
            int seqCnt = 0;
            StringBuilder seqBuilder = new StringBuilder();
            for (int j = i; j < i + seqLen; j++) {
                if (!customerId.equals(websiteRequests.get(j).customerId)) {
                    break;
                }
                seqBuilder.append(websiteRequests.get(j).pageVisited);
                if (++seqCnt == seqLen) {
                    String seq = seqBuilder.toString();
                    if (sequences.containsKey(seq)) {
                        int freq = sequences.get(seq);
                        sequences.put(seq, ++freq);
                    } else {
                        sequences.put(seq, 1);
                    }
                }
            }
        }
        TreeMap<String, Integer> sortedSequences = valueSort(sequences);
        int freq = -1;
        List<String> topPageSequences = new ArrayList<>();
        while (!sortedSequences.isEmpty()) {
            Map.Entry<String, Integer> entry = sortedSequences.pollLastEntry();
            if (freq == -1 || freq <= entry.getValue()) {
                topPageSequences.add(entry.getKey());
                freq = entry.getValue();
            } else {
                break;
            }
        }
        return topPageSequences;
    }

    public static void main(String argc[]) {
        List<WebsiteRequest> websiteRequests = new ArrayList<>();
        websiteRequests.add(getWebsiteRequest("CustomerA", 1, "A"));
        websiteRequests.add(getWebsiteRequest("CustomerA", 2, "B"));
        websiteRequests.add(getWebsiteRequest("CustomerA", 3, "C"));
        websiteRequests.add(getWebsiteRequest("CustomerA", 4, "D"));
        websiteRequests.add(getWebsiteRequest("CustomerA", 5, "E"));
        websiteRequests.add(getWebsiteRequest("CustomerA", 6, "F"));
        websiteRequests.add(getWebsiteRequest("CustomerB", 1, "E"));
        websiteRequests.add(getWebsiteRequest("CustomerB", 2, "B"));
        websiteRequests.add(getWebsiteRequest("CustomerB", 3, "C"));
        websiteRequests.add(getWebsiteRequest("CustomerB", 4, "D"));
        websiteRequests.add(getWebsiteRequest("CustomerB", 5, "A"));
        TopWebsiteRequests sol = new TopWebsiteRequests();
        List<String> res = sol.topPageSequence(websiteRequests, 3);
        res.stream().forEach(s -> System.out.println(s));
    }

    private static WebsiteRequest getWebsiteRequest(String customerId, long time, String pageVisited) {
        WebsiteRequest wr = new WebsiteRequest();
        wr.customerId = customerId;
        wr.time = time;
        wr.pageVisited = pageVisited;
        return wr;
    }
}
