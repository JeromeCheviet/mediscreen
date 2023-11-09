package com.mediscreen.massessment.proxy;

import com.mediscreen.massessment.bean.NoteBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "microservice-notes", url = "localhost:8082")
public interface MNotesProxy {
    @GetMapping(value = "/patHistory/{patId}")
    List<NoteBean> getPatHistory(@PathVariable("patId") int patId);
}
