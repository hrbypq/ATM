package org.jlu.atm.controller;

import org.jlu.atm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/adminLogIn/{username}/{password}")
    public int adminLogIn(@PathVariable String username, @PathVariable String password) {
        return adminService.adminLogIn(username, password);
    }

    @GetMapping("changeUserPassword/{cardID}/{previous}/{current}")
    public int changeUserPassword(@PathVariable String cardID, @PathVariable String previous,
                                  @PathVariable String current) {
        return adminService.changeUserPassword(cardID, previous, current);
    }
}
