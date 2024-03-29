package com.yhdx.wms.inbound.server.manager.impl;

import com.yhdx.wms.inbound.server.dao.mapper.PurchaseOrderAllocationMapper;
import com.yhdx.wms.inbound.server.dao.mapper.PurchaseOrderGoodsMapper;
import com.yhdx.wms.inbound.server.dao.mapper.PurchaseOrderMapper;
import com.yhdx.wms.inbound.server.domain.PurchaseOrder;
import com.yhdx.wms.inbound.server.domain.PurchaseOrderAllocation;
import com.yhdx.wms.inbound.server.domain.converter.PurchaseOrderAllocationConverter;
import com.yhdx.wms.inbound.server.domain.converter.PurchaseOrderConverter;
import com.yhdx.wms.inbound.server.domain.converter.PurchaseOrderGoodsConverter;
import com.yhdx.wms.inbound.server.domain.po.PurchaseOrderGoodsPO;
import com.yhdx.wms.inbound.server.domain.po.PurchaseOrderPO;
import com.yhdx.wms.inbound.server.manager.api.PurchaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;

@Service
public class PurchaseManagerImpl implements PurchaseManager {

    @Autowired
    private PurchaseOrderMapper purchaseOrderMapper;
    @Autowired
    private PurchaseOrderGoodsMapper purchaseOrderGoodsMapper;
    @Autowired
    private PurchaseOrderAllocationMapper purchaseOrderAllocationDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int savePurchaseOrder(PurchaseOrder purchaseOrder) throws RuntimeException  {
        PurchaseOrderPO purchaseOrderPO = PurchaseOrderConverter.toPO(purchaseOrder);
        // save success  purchase_order
        int affectedRows = purchaseOrderMapper.savePurchaseOrder(purchaseOrderPO);
        if(affectedRows > 0) {
            List<PurchaseOrderGoodsPO> purchaseOrderGoodsPOList = PurchaseOrderGoodsConverter.toPO(purchaseOrder.getGoodsList());
            for(PurchaseOrderGoodsPO purchaseOrderGoodsPO : purchaseOrderGoodsPOList) {
                purchaseOrderGoodsPO.setOrderId(purchaseOrderPO.getId());
            }
            // save success  purchase_order_goods
            purchaseOrderGoodsMapper.saveAllPurchaseOrderGoods(purchaseOrderGoodsPOList);
            // unchecked
            throw new RuntimeException("测试抛异常后，事务回滚！");
            // rollback
        }
        return affectedRows;
    }

    @Override
    public PurchaseOrder getPurchaseOrder(Long id) {
        PurchaseOrder purchaseOrder = PurchaseOrderConverter.fromPO(purchaseOrderMapper.getPurchaseOrder(id));
        if(purchaseOrder != null) {
            purchaseOrder.setGoodsList(PurchaseOrderGoodsConverter.fromPO(
                    purchaseOrderGoodsMapper.listPurchaseOrderGoodsByOrderId(id)));
        }
        return purchaseOrder;
    }

    @Override
    public int saveAllPurchaseOrderAllocation(Collection<PurchaseOrderAllocation> collection) {
        return purchaseOrderAllocationDao.saveAllPurchaseOrderAllocation(
                PurchaseOrderAllocationConverter.toPO(collection));
    }

}
