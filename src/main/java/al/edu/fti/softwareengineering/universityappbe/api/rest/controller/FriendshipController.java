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

    @PostMapping("/friendRequest")
    public ResponseEntity<Void> sendFriendRequest(@RequestBody Long idRequestedTo) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.getFriendshipService().addFriendship(userDetails.getId(), idRequestedTo);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/acceptFriendRequest")
    public ResponseEntity<Void> acceptFriendRequest(@RequestBody Long idRequestedBy) {
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

    @GetMapping("/friendships/loggedUser")
    public ResponseEntity<List<FriendshipDTO>> getFriendshipsOfLoggedUser() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(this.getFriendshipService().getFriendshipsOfAUser(userDetails.getId()));
    }

    @GetMapping("/exists/{idPossibleFriend}")
    public ResponseEntity<FriendshipDTO> doesFriendshipExist(@PathVariable("idPossibleFriend") Long idPossibleFriend) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(getFriendshipService().getFriendshipIfAlreadyExists(userDetails.getId(), idPossibleFriend));
    }

    private FriendshipService getFriendshipService() {
        return (FriendshipService) service;
    }
}
