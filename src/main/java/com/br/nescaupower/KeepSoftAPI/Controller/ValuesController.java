package com.br.nescaupower.KeepSoftAPI.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author developer
 */
@RestController
@RequestMapping("/api/values")
public class ValuesController {
    
    @GetMapping
    public String[] getAllUsuarios(){
        return new String[]{"values1", "values2"};
    }
}
