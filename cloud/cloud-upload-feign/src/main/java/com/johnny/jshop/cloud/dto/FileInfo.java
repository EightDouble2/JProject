package com.johnny.jshop.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件信息
 * <p>
 * Description:
 * </p>
 *
 * @class FileInfo
 * @author JohnnyHao
 * @date 2020-02-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo implements Serializable {

    // 文件路径
    private String path;
}
