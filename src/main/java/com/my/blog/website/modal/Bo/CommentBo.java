package com.my.blog.website.modal.Bo;

import com.my.blog.website.modal.Vo.CommentVo;

import java.util.List;

/**
 * ************************************
 *
 * @ClassName: CommentBo
 * @Auther: dangjinhu
 * @Date: 2018/2/23
 * @Description: 返回页面的评论，包含父子评论内容
 * @Copyright: All rights reserver.
 * ************************************
 */


public class CommentBo extends CommentVo {

    private int levels;
    private List<CommentVo> children;

    public CommentBo(CommentVo comments) {
        setAuthor(comments.getAuthor());
        setMail(comments.getMail());
        setCoid(comments.getCoid());
        setAuthorId(comments.getAuthorId());
        setUrl(comments.getUrl());
        setCreated(comments.getCreated());
        setAgent(comments.getAgent());
        setIp(comments.getIp());
        setContent(comments.getContent());
        setOwnerId(comments.getOwnerId());
        setCid(comments.getCid());
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public List<CommentVo> getChildren() {
        return children;
    }

    public void setChildren(List<CommentVo> children) {
        this.children = children;
    }
}
