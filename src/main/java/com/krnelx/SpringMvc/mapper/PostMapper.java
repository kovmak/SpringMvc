package com.krnelx.SpringMvc.mapper;

import com.krnelx.SpringMvc.dto.PostDTO;
import com.krnelx.SpringMvc.model.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDTO toDTO(Post post);
    Post toEntity(PostDTO postDTO);
}