package biz.netcentric.cq.tools.actool.honorservice;

import java.util.Map;
import java.util.Set;

/**
 * Created by pol on 02/02/2017.
 */
    public interface HonorService {

        /** method that sets up the service with the map of honorables.
         *
         */
        void setupService(Map<String, Set<String>> honorables);

        /** method that returns wheter if a group is honored for a path or not.
         *
         * @return {@code true} if honored, otherwise {@code false} */
        boolean checkHonorablesPathGroup(String path, String group);

}
