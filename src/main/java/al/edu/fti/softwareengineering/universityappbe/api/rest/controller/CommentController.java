package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.api.security.userDetails.MyUserDetails;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.ContentWrapperDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController extends CommonCrudRestController<CommentDTO, Long> {

    @PostMapping("/content/{idContent}")
    public ResponseEntity<Void> addCommentInAContent(@PathVariable("idContent") Long idContent, @RequestBody ContentWrapperDTO contentWrapperDTO) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.getCommentService().addCommentToACommentableAndLikeable(idContent, contentWrapperDTO.getContent(), userDetails.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/content/{idContent}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsInAContent(@PathVariable("idContent") Long idContent) {
        return ResponseEntity.ok(this.getCommentService().getAllCommentsInACommentableAndLikeable(idContent));
    }

    private CommentService getCommentService() {
        return (CommentService) service;
    }
}
