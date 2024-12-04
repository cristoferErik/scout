package com.checker.scout.controllers.home;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.home.interfaces.HomeInt;
import com.checker.scout.controllers.home.model_assembler.HomeMa;
import com.checker.scout.entities.projections.IWebSite;
import com.checker.scout.entities.projections.IWebSite.WebSiteP;
import com.checker.scout.services.ServizioWebSiteService;

@RestController
@RequestMapping("/restHome")
public class HomeRestController {
    @Autowired
    private ServizioWebSiteService servizioWebSiteService;

    @Autowired
    private HomeMa.WebSiteToUpdateMa webSiteToUpdateMa;

    @Autowired
    private PagedResourcesAssembler<IWebSite.WebSiteP> pagedResourcesAssembler;

    @GetMapping("/webSiteToUpdate")
    public Map<String,Object> getAllWebSiteToUpdate(
        @RequestParam (value= "page",defaultValue="0") Integer page,
        @RequestParam (value="size",defaultValue="10") Integer size){
        Map<String,Object> response= new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<WebSiteP> webSitePage=servizioWebSiteService.getAllWebSiteToUpdate(pageable);
        PagedModel<HomeInt.webSiteToUpdateDTO> resources= pagedResourcesAssembler.toModel(webSitePage,webSiteToUpdateMa);
        response.put("status","success");
        response.put("body",resources);
        return response;
    }
}
