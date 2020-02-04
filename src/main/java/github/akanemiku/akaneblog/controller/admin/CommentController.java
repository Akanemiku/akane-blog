package github.akanemiku.akaneblog.controller.admin;

import github.akanemiku.akaneblog.exception.InternalException;
import github.akanemiku.akaneblog.model.Comment;
import github.akanemiku.akaneblog.service.CommentService;
import github.akanemiku.akaneblog.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "")
    public String index(@RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                        @RequestParam(value = "pageSize", defaultValue = "1", required = false) Integer pageSize,
                        HttpServletRequest request) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Comment> comments = commentService.getAllComments(pageable);
        request.setAttribute("comments", comments);
        return "admin/comment_list";
    }

    @PostMapping(value = "/status")
    @ResponseBody
    public APIResponse changeStatus(@RequestParam(name = "coid", required = true) Integer coid,
                                    @RequestParam(name = "status", required = true) String status) {

        try {
            Comment comment = commentService.getCommentById(coid);
            if (null != comment) {
                commentService.updateComment(coid, status);
            } else {
                return APIResponse.failure("修改失败");
            }
        } catch (InternalException e) {
            return APIResponse.failure(e.getMessage());
        }
        return APIResponse.success();
    }
}
