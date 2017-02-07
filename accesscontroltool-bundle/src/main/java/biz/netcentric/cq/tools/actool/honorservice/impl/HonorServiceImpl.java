package biz.netcentric.cq.tools.actool.honorservice.impl;

import biz.netcentric.cq.tools.actool.honorservice.HonorService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;

import java.util.Map;
import java.util.Set;

/**
 * Created by pol on 02/02/2017.
 */

@Service
@Component(enabled = true, immediate = true, metatype = true)

public class HonorServiceImpl implements HonorService {


    private Map<String, Set<String>> honorables;

    @Override public void setupService(Map<String, Set<String>> honorables) {
        this.honorables = honorables;
    }

    @Override public boolean checkHonorablesPathGroup(String path, String group) {
        Set<String> honoredPaths = honorables.get(group);
        Boolean isHonored = false;
        if(honoredPaths != null) {
            isHonored = honoredPaths.contains(path);
        }
        return isHonored;
    }

    public void flush(){

    }
}
