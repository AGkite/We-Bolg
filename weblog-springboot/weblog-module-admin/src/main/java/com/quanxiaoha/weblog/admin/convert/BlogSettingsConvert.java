package com.quanxiaoha.weblog.admin.convert;


import com.quanxiaoha.weblog.admin.model.vo.blogsettings.FindBlogSettingsRspVO;
import com.quanxiaoha.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.quanxiaoha.weblog.common.domain.dos.BlogSettingsDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogSettingsConvert {
    /**
     * 初始华convert实例
     */
    BlogSettingsConvert INSTANCE = Mappers.getMapper(BlogSettingsConvert.class);

    /**
     * 将 VO 转为 DO
     * @param bean
     * @return
     */
    BlogSettingsDO convertVO2DO(UpdateBlogSettingsReqVO bean);

    /**
     * 将DO转为VO
     * @param bean
     * @return {@link FindBlogSettingsRspVO}
     */
    FindBlogSettingsRspVO convertDO2VO(BlogSettingsDO bean);
}
