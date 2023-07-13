package com.wukongnotnull.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wukongnotnull.shop.common.Constants;
import com.wukongnotnull.shop.common.IndexConfigTypeEnum;
import com.wukongnotnull.shop.controller.vo.IndexGoodsVO;
import com.wukongnotnull.shop.domain.GoodsDetail;
import com.wukongnotnull.shop.domain.IndexConfig;
import com.wukongnotnull.shop.mapper.GoodsDetailMapper;
import com.wukongnotnull.shop.service.IndexConfigService;
import com.wukongnotnull.shop.mapper.IndexConfigMapper;
import com.wukongnotnull.shop.util.BeanUtil;
import com.wukongnotnull.shop.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 悟空非空也
* @description 针对表【shop_index_config】的数据库操作 Service 实现
*/
@Service
public class IndexConfigServiceImpl extends ServiceImpl<IndexConfigMapper, IndexConfig>
    implements IndexConfigService{
    @Autowired
    private IndexConfigMapper indexConfigMapper;
    @Autowired
    private GoodsDetailMapper goodsDetailMapper;

    /**
     * 获取首页各个模块（热品售卖、新品推荐、为你推荐等）的商品列表
     * @return List<IndexGoodsVO>
     */
    @Override
    public List<IndexGoodsVO> getIndexGoodsModule(int configType,int goodsNum) {
        List<IndexGoodsVO> indexGoodsVOList = new ArrayList<>();
        // 获得首页配置列表
        List<IndexConfig> indexConfigList =  indexConfigMapper.
                findIndexConfigList(configType,goodsNum);

        if(!CollectionUtils.isEmpty(indexConfigList)){
            // 根据配置列表，获得 goods_id 列表
            List<Long> goodsIdList = indexConfigList.stream().
                    map(IndexConfig::getGoodsId).
                    collect(Collectors.toList());

            // 根据 goods_id 列表查询对应的 goods_detail 商品信息列表
            List<GoodsDetail> goodsDetailList = goodsDetailMapper.getGoodsDetailList(goodsIdList);

            if(!CollectionUtils.isEmpty(goodsDetailList)){
                // do to vo
                indexGoodsVOList = BeanUtil.copyList(goodsDetailList, IndexGoodsVO.class);

                // 对 indexGoodsVO 对象中的 g
                // goods_name 和 goods_intro 字符长度进行限制
                for(IndexGoodsVO indexGoodsVO: indexGoodsVOList){
                    String goodsName = indexGoodsVO.getGoodsName();
                    String goodsIntro = indexGoodsVO.getGoodsIntro();
                    String tmpGoodsName = StringUtil.subStringUseEllipsis(goodsName, Constants.INDEX_MODULE_GOODS_NAME_LENGTH);
                    String tmpGoodsIntro = StringUtil.subStringUseEllipsis(goodsIntro, Constants.INDEX_MODULE_GOODS_INTRO_LENGTH);
                    indexGoodsVO.setGoodsName(tmpGoodsName);
                    indexGoodsVO.setGoodsIntro(tmpGoodsIntro);

                }

            }

        }
        return  indexGoodsVOList;
    }




}




