package al.edu.fti.softwareengineering.universityappbe.api.rest.controller;

import al.edu.fti.softwareengineering.universityappbe.api.rest.controller.common.CommonCrudRestController;
import al.edu.fti.softwareengineering.universityappbe.api.security.userDetails.MyUserDetails;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.CommentBodyDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.dtos.userInteractions.CommentDTO;
import al.edu.fti.softwareengineering.universityappbe.core.business.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController extends CommonCrudRestController<CommentDTO, Long> {

    @PostMapping("/content/{idContent}")
    public ResponseEntity<Void> addCommentInAContent(@PathVariable("idContent") Long idContent, @RequestBody CommentBodyDTO commentBodyDTO) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        this.getCommentService().addCommentToACommentableAndLikeable(idContent, commentBodyDTO.getContent(), userDetails.getId());
        return ResponseEntity.ok().build();
    }

    private CommentService getCommentService() {
        return (CommentService) service;
    }
}
