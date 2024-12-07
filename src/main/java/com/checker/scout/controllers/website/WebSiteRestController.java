package com.checker.scout.controllers.website;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.checker.scout.controllers.website.interfaces.WebSiteInt;
import com.checker.scout.entities.WebSite;
import com.checker.scout.entities.projections.IDetailWsSe;
import com.checker.scout.repositories.DetailWsSeRepository;
import com.checker.scout.services.ServizioService;
import com.checker.scout.services.WebSiteService;
import com.checker.scout.util.paginator.PageRender;

@RequestMapping("/restWebSite")
@RestController
public class WebSiteRestController {

    @Autowired
    private WebSiteService webSiteService;

    @Autowired
    private DetailWsSeRepository detailWsSeRepository;

    @Autowired
    private ServizioService servizioService;

    @GetMapping("/webSites")
    public List<WebSite> getServizi(@RequestParam Long hostingId) {
        //System.out.println("rest-->"+hostingId);
        return webSiteService.findAllWebSite(hostingId);
    }

    @PostMapping("/saveWebSite")
    public Map<String, Object> saveWebSite(@RequestBody WebSiteInt.WebSiteDao webSiteDao) {
        return webSiteService.saveWebSite(webSiteDao);
    }

    @GetMapping("/webSite/{id}")
    public Map<String, Object> getWebSiteById(@PathVariable Long id) {
        Optional<WebSite> optWebSite = this.webSiteService.getWebSiteById(id);
        Map<String, Object> response = new HashMap<>();
        if (optWebSite.isPresent()) {
            response.put("status", "success");
            response.put("body", optWebSite.get());
        } else {
            response.put("status", "not_found");
            response.put("message", "Il web site non si é trovato nella base di dati");
        }
        return response;
    }

    @DeleteMapping("/webSite/{id}")
    public Map<String, Object> deleteWebSite(@PathVariable Long id) {
        return this.webSiteService.deleteWebSite(id);
    }

    @GetMapping("/historial")
    public ResponseEntity<?> ServiceByWebSite(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam Long webSiteId) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, size);
        Page<IDetailWsSe.HistorialService> hist = this.detailWsSeRepository.findAllHistorialServicesByWebSiteId(webSiteId, pageable);
        PageRender pageRender = new PageRender(page, hist.getTotalPages(), size);
        List<Integer> listNumbers = pageRender.getPageNumbers();

        Map<String, Object> pageLinks = pageRender.generatePageLink("/restWebSite/historial", listNumbers);

        response.put("status", "success");
        response.put("body", hist.getContent());
        response.put("pageLinks", pageLinks);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/historial/{id}")
    public ResponseEntity<?> ServizioForWebSiteById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        if (id == null) {
            response.put("status", "bad_request");
            response.put("message", "L'id non può essere nullo");
            ResponseEntity.status(HttpStatus.OK).body(response);
        }
        response = servizioService.getServizioForWebSiteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @DeleteMapping("/historial/{id}")
    public ResponseEntity<?> deleteServizioForWebSiteById(@PathVariable Long id){
        Map<String,Object> response=new HashMap<>();
        if(id==null){
            response.put("status","bad_request");
            response.put("message","L'id della riga selezionata è null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
         response= servizioService.deleteServizioForWebSiteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
