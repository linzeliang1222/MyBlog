package top.linzeliang.blog.controller.link;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.linzeliang.blog.dto.link.LinkShow;
import top.linzeliang.blog.entity.Link;
import top.linzeliang.blog.service.LinkService;

import java.util.ArrayList;

/**
 * @Description: TODO
 * @Author: linzeliang
 * @Date: 2021/8/26
 */
@Controller
@Api("前台友链相关Controller")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @RequestMapping(value = {"/link.html", "/link.htm"}, method = RequestMethod.GET)
    @ApiOperation("友链详情")
    public String linkList(Model model) {

        ArrayList<LinkShow> links = new ArrayList<>();
        for (Link l : linkService.listLinks()) {
            LinkShow link = new LinkShow(
                    l.getLinkName(),
                    l.getLinkSite(),
                    l.getLinkAvatar(),
                    l.getLinkDescription()
            );

            links.add(link);
        }
        model.addAttribute("links", links);

        return "link";
    }
}
