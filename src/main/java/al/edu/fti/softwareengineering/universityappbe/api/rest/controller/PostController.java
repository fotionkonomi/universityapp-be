package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.api.security.userDetails.MyUserDetails;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.ContentWrapperDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.commentableAndLikeable.PostDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController extends CommonCrudRestController<PostDTO, Long> {

    @PostMapping("/timeline")
    public ResponseEntity<Void> postToTimeline(@RequestBody ContentWrapperDTO postedContent) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        getPostService().addPostToTimeline(postedContent.getContent(), userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/showLoggedUser/{pageNumber}")
    public ResponseEntity<List<PostDTO>> postsToShowLoggedUser(@PathVariable("pageNumber") int pageNumber) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(getPostService().getPostsToShowToLoggedUser(userDetails.getId(), pageNumber));
    }

    @GetMapping("/timeline/{pageNumber}")
    public ResponseEntity<List<PostDTO>> getPostsOfLoggedUser(@PathVariable("pageNumber") int pageNumber) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(getPostService().getPostsOfAUser(userDetails.getId(), pageNumber));
    }

    private PostService getPostService() {
        return (PostService) service;
    }
}
