package net.xdclass.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @projectName: xdclass-1024-shop
 * @package: net.xdclass.service
 * @className: UserService
 * @author: duruijuan
 * @description:
 * @since: 2025-06-07 17:09
 * @version: 1.0
 */
public interface FileService {
    String uploadUserImg(MultipartFile file);
}
