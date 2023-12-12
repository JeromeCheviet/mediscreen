package com.mediscreen.massessment.proxy;

import com.mediscreen.massessment.bean.NoteBean;
import com.mediscreen.massessment.configuration.CustomFeignClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Interface used to interact with microservice-notes
 */
@FeignClient(name = "microservice-notes", configuration = CustomFeignClientConfiguration.class)
@RibbonClient(name = "microservice-notes")
public interface MNotesProxy {

    /**
     * Get the patient historic
     *
     * @param patId the patient ID
     * @return the list of patient notes
     */
    @GetMapping(value = "/patHistory/{patId}")
    List<NoteBean> getPatHistory(@PathVariable("patId") int patId);
}
