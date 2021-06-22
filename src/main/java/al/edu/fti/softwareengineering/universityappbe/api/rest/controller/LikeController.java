package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.api.security.userDetails.MyUserDetails;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.LikeDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/like")
public class LikeController extends CommonCrudRestController<LikeDTO, Long> {

    @PostMapping("/comment")
    public ResponseEntity<Void> toggleLikeAComment(@RequestBody Long idComment) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        getLikeService().toggleLikeAComment(idComment, userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/content")
    public ResponseEntity<Void> toggleLikeAContent(@RequestBody Long idContent) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        getLikeService().toggleLikeContent(idContent, userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comment/isLiked/{idComment}")
    public ResponseEntity<Boolean> findIfACommentIsAlreadyLiked(@PathVariable("idComment") Long idComment) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(this.getLikeService().getLikeIfCommentIsAlreadyLiked(idComment, userDetails.getId()) != null ? true : false);
    }

    @GetMapping("/content/isLiked/{idContent}")
    public ResponseEntity<Boolean> findIfAContentIsAlreadyLiked(@PathVariable("idContent") Long idContent) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(this.getLikeService().getLikeIfContentIsAlreadyLiked(idContent, userDetails.getId()) != null ? true : false);
    }

    @GetMapping("/comment/{idComment}")
    public ResponseEntity<List<LikeDTO>> findAllLikesOfAComment(@PathVariable("idComment") Long idComment) {
        return ResponseEntity.ok(this.getLikeService().getLikesOfAComment(idComment));
    }

    @GetMapping("/content/{idContent}")
    public ResponseEntity<List<LikeDTO>> findAllLikesOfAContent(@PathVariable("idContent") Long idContent) {
        return ResponseEntity.ok(this.getLikeService().getLikesOfAContent(idContent));
    }

    public LikeService getLikeService() {
        return (LikeService) service;
    }
}
