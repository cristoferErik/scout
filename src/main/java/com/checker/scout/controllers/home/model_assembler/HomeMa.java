package com.checker.scout.controllers.home.model_assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.checker.scout.controllers.home.HomeRestController;
import com.checker.scout.controllers.home.interfaces.HomeInt.webSiteToUpdateDTO;
import com.checker.scout.entities.projections.IWebSite.WebSiteP;


public interface HomeMa {
    @Component
    public class WebSiteToUpdateMa extends RepresentationModelAssemblerSupport<WebSiteP, webSiteToUpdateDTO>{

        public WebSiteToUpdateMa() {
            super(HomeRestController.class, webSiteToUpdateDTO.class);
        }
        
        @Override
        public webSiteToUpdateDTO toModel(WebSiteP webSiteP) {
            webSiteToUpdateDTO wsuDTO= new webSiteToUpdateDTO();
            wsuDTO.setId(webSiteP.getId());
            wsuDTO.setNomeWebSite(webSiteP.getNomeWebSite());
            wsuDTO.setProssimoAgg(webSiteP.getProssimoAgg());
            wsuDTO.setNomeServizio(webSiteP.getNomeServizio());

            return wsuDTO;
        }

    }
}
