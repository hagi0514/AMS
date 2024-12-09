package Auction.AMS.auction.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Auction.AMS.auction.entity.Sample;
import Auction.AMS.auction.service.SampleService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import payload.endpoint.Endpoint;

@CrossOrigin(origins=Endpoint.URL ,maxAge=3600, allowCredentials="true")
@RestController
@RequestMapping("api/sample")public class SampleController {
    @Autowired
    private SampleService sampleService;

    @GetMapping("/sampleList")
    public ResponseEntity<List<Sample>>sampleList(){
        System.out.println(" i am controller");
        try{
            List<Sample> sampleList=sampleService.sampleList();
            System.out.println(" i am controller");
            return new ResponseEntity<>(sampleList,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @GetMapping("/getSampleById/{id}")
    public ResponseEntity<Sample> getSampleById(@PathVariable("id") Long id){
        try{
            Sample sampleById = sampleService.getSampleById(id);
            return new ResponseEntity<>(sampleById,HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
    @PostMapping("/addSample")
    public ResponseEntity<HttpStatus>addSample(@RequestBody Sample sample){
        try{
            sampleService.addSample(sample);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            System.out.println(e);

            
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateSample/{id}")
    public ResponseEntity<HttpStatus>updateSample(@PathVariable("id") Long id, @RequestBody Sample sample){
        try {
            sampleService.updateSample(sample);
            System.out.println("here is the sample i updated and i am controller"+sample);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
