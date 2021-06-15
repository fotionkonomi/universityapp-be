package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;


import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.api.security.userDetails.MyUserDetails;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.FriendshipDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.FriendshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friendship")
public class FriendshipController extends CommonCrudRestController<FriendshipDTO, Long> {

    @PostMapping("/friendRequest/{idRequestedTo}")
    public ResponseEntity<Void> sendFriendRequest(@PathVariable("idRequestedTo") Long idRequestedTo) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.getFriendshipService().addFriendship(userDetails.getId(), idRequestedTo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/acceptFriendRequest/{idRequestedBy}")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable("idRequestedBy") Long idRequestedBy) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.getFriendshipService().acceptFriendRequest(userDetails.getId(), idRequestedBy);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/declineFriendRequest/{idRequestedBy}")
    public ResponseEntity<Void> delcineFriendRequest(@PathVariable("idRequestedBy") Long idRequestedBy) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.getFriendshipService().declineFriendRequest(userDetails.getId(), idRequestedBy);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/friendRequests/loggedUser")
    public ResponseEntity<List<FriendshipDTO>> getFriendshipRequestedToLoggedUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(this.getFriendshipService().getFriendshipRequestsForAUser(userDetails.getId()));
    }

    private FriendshipService getFriendshipService() {
        return (FriendshipService) service;
    }
}
