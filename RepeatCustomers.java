import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dhssn on 11/5/16.
 */
public class RepeatCustomers {

    private static final long SECONDS_IN_DAY = 60L * 60L * 24L;
    private static final String[] customerIds = {"ABC", "BCD", "CDE", "DEF", "EFG"};


    private static class CustomerTimestamp implements Comparable<CustomerTimestamp> {

        public String customerId;
        public long timestamp;

        @Override
        public int compareTo(final CustomerTimestamp other) {
            final int customerIdsCompared = this.customerId.compareTo(other.customerId);
            if (customerIdsCompared == 0) {
                if (this.timestamp < other.timestamp) {
                    return -1;
                } else if (this.timestamp > other.timestamp) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return customerIdsCompared;
            }
        }

        public String toString() {
            return this.customerId + " " + Long.toString(this.timestamp);
        }
    }

    public static void main(final String argc[]) {

        final List<CustomerTimestamp> customerTimestampList = new ArrayList<>();
        final CustomerTimestamp ct = new CustomerTimestamp();
        ct.customerId = customerIds[0];
        ct.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        customerTimestampList.add(ct);
        final CustomerTimestamp ct1 = new CustomerTimestamp();
        ct1.customerId = customerIds[1];
        ct1.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        customerTimestampList.add(ct1);
        final CustomerTimestamp ct2 = new CustomerTimestamp();
        ct2.customerId = customerIds[2];
        ct2.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        customerTimestampList.add(ct2);
        final CustomerTimestamp ct3 = new CustomerTimestamp();
        ct3.customerId = customerIds[3];
        ct3.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        customerTimestampList.add(ct3);
        final CustomerTimestamp ct4 = new CustomerTimestamp();
        ct4.customerId = customerIds[4];
        ct4.timestamp = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        customerTimestampList.add(ct4);
        final CustomerTimestamp ct5 = new CustomerTimestamp();
        ct5.customerId = customerIds[0];
        ct5.timestamp = LocalDateTime.now().minusDays(1L).toEpochSecond(ZoneOffset.UTC);
        customerTimestampList.add(ct5);

        final RepeatCustomers rc = new RepeatCustomers();
//        rc.findRepeatCustomerBySorting(customerTimestampList);
        rc.findRepeatCustomersByMultimap(customerTimestampList);
    }

    public List<String> findRepeatCustomersByMultimap(final List<CustomerTimestamp> customerTimestampList) {

        customerTimestampList.stream().forEach(ct -> ct.timestamp = truncateToDay(ct.timestamp));
        final Set<String> uniqueCustomerIds = new HashSet<>();
        customerTimestampList.forEach(ct -> uniqueCustomerIds.add(ct.customerId));

        final Multimap m = new Multimap(2);
        customerTimestampList.forEach(ct -> m.put(ct.customerId, ct.timestamp));
        m.remove("ABC");

        final List<String> repeatCustomers = new ArrayList<>();
        for (final String customerId : uniqueCustomerIds) {
            List<Long> timestamps = m.get(customerId);
            Collections.sort(timestamps);
            Long prev = null;
            for (final Long t : timestamps) {
                if (prev != null) {
                    if (timestampsOnConcurrentDay(prev, t)) {
                        repeatCustomers.add(customerId);
                    }
                }
                prev = t;
            }
        }
        repeatCustomers.stream().forEach(s -> System.out.println(s));


        return repeatCustomers;
    }

    public List<String> findRepeatCustomersBySorting(final List<CustomerTimestamp> customerTimestampList) {

        customerTimestampList.stream().forEach(ct -> ct.timestamp = truncateToDay(ct.timestamp));
//        printCustomerTimestampList(customerTimestampList);
        Collections.sort(customerTimestampList);
        CustomerTimestamp prev = null;
        final List<String> repeatCustomers = new ArrayList<>();
        for (final CustomerTimestamp ct : customerTimestampList) {
            if (prev != null) {
                if (prev.customerId.equals(ct.customerId)
                        && timestampsOnConcurrentDay(prev.timestamp, ct.timestamp)) {
                    repeatCustomers.add(ct.customerId);
                }
            }
            prev = ct;
        }
        repeatCustomers.stream().forEach(s -> System.out.println(s));
        return repeatCustomers;
    }

    private long truncateToDay(final long timestamp) {

        final long fractionOfDay = timestamp % SECONDS_IN_DAY;
        return timestamp - fractionOfDay;
    }

    private boolean timestampsOnConcurrentDay(final long t1, final long t2) {
        return Math.abs(t2 - t1) == SECONDS_IN_DAY;
    }

    private void printCustomerTimestampList(final List<CustomerTimestamp> customerTimestampList) {

        customerTimestampList.stream().forEach(ct -> System.out.println(ct));
    }
}
