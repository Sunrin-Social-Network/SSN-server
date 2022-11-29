package io.twotle.ssn.controller;

import com.google.firebase.auth.FirebaseAuthException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.twotle.ssn.component.CustomException;
import io.twotle.ssn.component.ExceptionCode;
import io.twotle.ssn.dto.*;
import io.twotle.ssn.entity.Comment;
import io.twotle.ssn.entity.Post;
import io.twotle.ssn.entity.User;
import io.twotle.ssn.jwt.AccountDetails;
import io.twotle.ssn.service.AuthService;
import io.twotle.ssn.service.BoardService;
import io.twotle.ssn.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/board")
@Api(tags = {"3. Board"})
public class BoardController {
    private final BoardService boardService;
    private final StorageService storageService;

    @ApiOperation(value = "Create Comment")
    @PostMapping("/{boardId}/comment")
    @Transactional
    public ResponseEntity<ResultResponseDTO> createComment(
            @AuthenticationPrincipal @ApiIgnore AccountDetails accountDetails,
            @PathVariable("boardId") Long boardId,
            @RequestBody CommentDTO body
    ) {
        //Long bId = Long.parseLong(boardId);
        User user = this.boardService.getUserByEmail(accountDetails.getUser().getEmail());
        Comment comment = this.boardService.createComment(user, body.getComment(),boardId);
        if(comment == null) throw new CustomException(ExceptionCode.DEFAULT_ERROR);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultResponseDTO(true));


    }

    @ApiOperation(value = "Create Post")
    @PostMapping("/")
    @Transactional
    public ResponseEntity<ResultResponseDTO> createPost(
            @AuthenticationPrincipal @ApiIgnore AccountDetails accountDetails,
            @ModelAttribute PostDTO body
            ) {

        if(body.getFile().isEmpty()) throw new CustomException(ExceptionCode.NO_FILE_ERROR);
        User user = this.boardService.getUserByEmail(accountDetails.getUser().getEmail());
        String filename = this.storageService.generateFilename(user, body.getFile());
        String url = "https://firebasestorage.googleapis.com/v0/b/sunrin-social-network.appspot.com/o/"+filename+"?alt=media";
        try {
            this.storageService.uploadFiles(body.getFile(), filename);
        } catch (IOException | FirebaseAuthException e) {
            throw new CustomException(ExceptionCode.DEFAULT_ERROR);
        }

        Post p = this.boardService.createPost(
                user,
                body.getText(),
                url,
                body.getTags()
        );
        if(p == null) throw new CustomException(ExceptionCode.DEFAULT_ERROR);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResultResponseDTO(true));

    }


    @ApiOperation(value = "Get Posts")
    @GetMapping("/")
    @Transactional
    public ResponseEntity<PostsDTO> getPosts() {
        List<JPostDTO> data = this.boardService.getPosts();
        PostsDTO ret = new PostsDTO(data);
        return ResponseEntity.status(HttpStatus.OK).body(ret);
    }

    @ApiOperation(value = "Get One Post")
    @GetMapping("/{boardId}")
    @Transactional
    public ResponseEntity<JPostDTO> getPost(
            @PathVariable("boardId") Long id
    ) {
        JPostDTO data = this.boardService.getPost(id);
        return ResponseEntity.status(HttpStatus.OK).body(data);
    }


}
