package com.formation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formation.dao.ICatalogDAO;
import com.formation.domain.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/cats")
public class CatalogController {

        private static final Logger LOG = Logger.getLogger(CatalogController.class.getSimpleName());

        @Autowired
        private ICatalogDAO catalogService;

        @Autowired
        private CounterService counterService;

        @Autowired
        private GaugeService gaugeService;

        @RequestMapping(method = RequestMethod.GET, value = "/catalog", produces = "application/json")

        public String getCatalogName() {
            return catalogService.getCatalogName();
        }

        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity add(@RequestBody Cat newCat) {
            try {
                LOG.info("Adding cat " + new ObjectMapper().writeValueAsString(newCat));
            } catch (JsonProcessingException e) {
                LOG.severe("Parsing error");
            }

            catalogService.add(newCat);
            return ResponseEntity.ok().build();
        }

        @RequestMapping(method = RequestMethod.GET, value = "/{name}", produces = "application/json")
        public Cat get(@PathVariable String name) {
            LOG.info("Fetching cat " + name);

            return catalogService.get(name).orElse(null);
        }

        @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
        public Cat update( Cat newCat) {
            return catalogService.update(newCat);
        }

        @RequestMapping(method = RequestMethod.DELETE, value = "/{name}", produces = "application/json")
        public void delete(@PathVariable String name) {
            counterService.increment("api.cats.delete.counter");
            gaugeService.submit("api.cats.delete.gauge", getCats().size());
            catalogService.delete(name);
        }

        @RequestMapping(method = RequestMethod.GET, produces = "application/json")
        public List<Cat> getCats() {
            counterService.increment("api.cats");

            return catalogService.get().orElse(new ArrayList<>());
        }
}
