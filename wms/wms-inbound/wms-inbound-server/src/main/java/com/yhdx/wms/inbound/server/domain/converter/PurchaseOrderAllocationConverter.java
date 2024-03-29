package com.yhdx.wms.inbound.server.domain.converter;



import com.yhdx.wms.framework.util.BeanUtilsEx;
import com.yhdx.wms.inbound.server.domain.PurchaseOrderAllocation;
import com.yhdx.wms.inbound.server.domain.po.PurchaseOrderAllocationPO;

import java.util.Collection;
import java.util.List;

public class PurchaseOrderAllocationConverter {

    public final static PurchaseOrderAllocationPO toPO(PurchaseOrderAllocation source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderAllocationPO::new);
    }

    public final static List<PurchaseOrderAllocationPO> toPO(Collection<PurchaseOrderAllocation> source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderAllocationPO::new);
    }

    public final static PurchaseOrderAllocation fromPO(PurchaseOrderAllocationPO source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderAllocation::new, false);
    }

    public final static List<PurchaseOrderAllocation> fromPO(Collection<PurchaseOrderAllocationPO> source) {
        return BeanUtilsEx.copyProperties(source, PurchaseOrderAllocation::new, false);
    }

}
