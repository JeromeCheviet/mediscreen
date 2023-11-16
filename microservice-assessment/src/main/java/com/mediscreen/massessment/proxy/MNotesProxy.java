package com.mediscreen.massessment.proxy;

import com.mediscreen.massessment.bean.NoteBean;
import com.mediscreen.massessment.configuration.CustomFeignClientConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-notes", configuration = CustomFeignClientConfiguration.class)
@RibbonClient(name = "microservice-notes")
public interface MNotesProxy {
    @GetMapping(value = "/patHistory/{patId}")
    List<NoteBean> getPatHistory(@PathVariable("patId") int patId);
}
