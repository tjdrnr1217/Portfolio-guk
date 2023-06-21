package com.finalproject.SG.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.finalproject.SG.dto.Client;


@Mapper
public interface OrcaClientMapper {

    @Select({
		" SELECT * FROM client WHERE ID = #{id} "
	})
	public Client selectClientone(String id);
}
