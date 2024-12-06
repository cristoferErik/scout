package com.checker.scout.controllers.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.home.interfaces.HomeInt;
import com.checker.scout.entities.projections.IWebSite;
import com.checker.scout.entities.projections.IWebSite.WebSiteP;
import com.checker.scout.services.ServizioWebSiteService;
import com.checker.scout.util.paginator.PageRender;

@RestController
@RequestMapping("/restHome")
public class HomeRestController {
    @Autowired
    private ServizioWebSiteService servizioWebSiteService;

    @Autowired
    private PagedResourcesAssembler<IWebSite.WebSiteP> pagedResourcesAssembler;

    @GetMapping("/webSiteToUpdate")
    public ResponseEntity<?> getAllWebSiteToUpdate(
        @RequestParam (value= "page",defaultValue="0") Integer page,
        @RequestParam (value="size",defaultValue="10") Integer size){
        Map<String,Object> response= new HashMap<>();
        Pageable pageable = PageRequest.of(page,size);
        Page<WebSiteP> webSitePage=servizioWebSiteService.getAllWebSiteToUpdate(pageable);
        Page<HomeInt.webSiteToUpdateDTO> pageWebSiteUpdateDTO = webSitePage.map(objeto -> {
            
            HomeInt.webSiteToUpdateDTO dto= new HomeInt.webSiteToUpdateDTO();
            dto.setId(objeto.getId());
            dto.setNomeWebSite(objeto.getNomeWebSite());
            dto.setProssimoAgg(objeto.getProssimoAgg());
            dto.setNomeServizio(objeto.getNomeServizio());
            
            return dto;
        });
        PageRender pageRender= new PageRender(page, pageWebSiteUpdateDTO.getTotalPages(),size);
        
        List<Integer> listNumbers= pageRender.getPageNumbers();
        
        Map<String,Object> pageLinks = pageRender.generatePageLink("/restHome/webSiteToUpdate",listNumbers);

        response.put("status","success");
        response.put("body",pageWebSiteUpdateDTO.getContent());
        response.put("pageLinks", pageLinks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
