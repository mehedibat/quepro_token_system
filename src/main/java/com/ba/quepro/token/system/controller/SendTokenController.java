package com.ba.quepro.token.system.controller;


import com.ba.quepro.token.system.bean.RemoteTokenBean;
import com.ba.quepro.token.system.service.SendTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RequestMapping
@AllArgsConstructor
@RestController
public class SendTokenController {

    private final SendTokenService sendTokenService;

    @GetMapping("get-service-info")
    public ResponseEntity<?> getServiceData() throws Exception {
        return new ResponseEntity<>(sendTokenService.getServiceData(), HttpStatus.OK);
    }

    @GetMapping("get-service-center-info")
    public ResponseEntity<?> getServiceCenterData() {
        return new ResponseEntity<>(sendTokenService.getServiceCenterData(), HttpStatus.OK);
    }

    @PostMapping("remote-token")
    public ResponseEntity<?> saveTokenInfo(@RequestBody @Valid RemoteTokenBean bean) {
        sendTokenService.saveRemoteTokenInfo(bean);
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }


}
