package Auction.AMS.auction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Auction.AMS.auction.entity.Company;
import Auction.AMS.auction.service.CompanyService;
import payload.endpoint.Endpoint;


@CrossOrigin(origins=Endpoint.URL ,maxAge=3600, allowCredentials="true")
@RestController
@RequestMapping("api/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
@PostMapping("/saveCompany")
public ResponseEntity<HttpStatus>saveCompany(@RequestBody Company company){
    
    try{
        companyService.saveCompany(company);
		return new ResponseEntity<>(HttpStatus.OK);
    }catch(Exception ex){
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
     }
  }

  @PutMapping("/updateCompany/{id}")
  public ResponseEntity<HttpStatus>updateCompany(@PathVariable("id") Long id, @RequestBody Company company){
try{
    Company existingCompany=companyService.getCompanyById(id);
    if(existingCompany!=null){
        System.out.println("Update is called"+company);
        companyService.updateCompany(company);
		return new ResponseEntity<>(HttpStatus.OK);
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // If CPO not found

    }
} catch (Exception ex) {
    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    
}
  }

@PutMapping("/deleteCompany/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable("id") Long id ){
       companyService.deleteCompany(id);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/companyList")
    public ResponseEntity<List<Company>> companyList(){
        try{
            companyService.companyList();
            return new ResponseEntity<>(companyService.companyList(),HttpStatus.OK);
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   @GetMapping("/getCompanyById/{id}")
   public ResponseEntity<Company> getCompayById (@PathVariable("id")Long id){
    try {
          companyService.getCompanyById(id);
        return new ResponseEntity<>(companyService.getCompanyById(id),HttpStatus.OK);
    } catch (Exception e) {

         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
   }

}