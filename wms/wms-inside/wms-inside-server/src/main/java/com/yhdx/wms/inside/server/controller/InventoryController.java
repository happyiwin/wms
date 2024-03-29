package com.yhdx.wms.inside.server.controller;


import com.yhdx.wms.framework.model.Result;
import com.yhdx.wms.framework.model.tuples.Triple;
import com.yhdx.wms.inside.domain.InventoryChangeRequest;
import com.yhdx.wms.inside.feign.api.InventoryClient;
import com.yhdx.wms.inside.server.service.api.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class InventoryController implements InventoryClient {

    @Autowired
    private InventoryService inventoryService;

    @Override
    public Result<?> updateInvertory(@RequestBody InventoryChangeRequest request) {
        inventoryService.updateInventory(request);
        return Result.ok();
    }

    @Override
    public Result<?> updateAllInvertory(@RequestBody Collection<InventoryChangeRequest> collection) {
        inventoryService.updateInventory(collection);
        return Result.ok();
    }

    @Override
    public Result<List<Triple<Long, Long, Long>>> listAvailableLocation() {
        List<Triple<Long, Long, Long>> tripleList = new ArrayList<>();
        for(int x = 0; x < 3; x++) {
            for(int y = 0; y < 3; y++) {
                for(int z = 0; z < 3; z++) {
                    tripleList.add(new Triple<>((long)x, (long)y, (long)z));
                }
            }
        }
        return Result.ok(tripleList);
    }


    /// ********** tests **********

//    @PostMapping("test/inventory/update-lock")
//    public Result<?> testUpdateInvertoryLock() {
//        List<InventoryChangeRequest> requestList = new ArrayList<>();
//        Random random = new Random();
//        for(int i = 0; i < 3; i++) {
//            InventoryChangeRequest request = new InventoryChangeRequest();
//            request.setInventoryId(new InventoryId((long)1,(long)1,(long)1,(long)1));
//            request.setOperationType(InventoryOperationTypeEnum.ADDITION);
//            request.setOperationId(1L);
//            request.setBizId(1L);
//            request.setCount(1);
//            requestList.add(request);
//        }
//        requestList.parallelStream().forEach((request -> inventoryService.updateInventory(request)));
//        return Result.ok();
//    }
//
//    @PostMapping("test/inventory/update-parallel")
//    public Result<?> testUpdateInvertoryParallel() {
//        List<InventoryChangeRequest> requestList = new ArrayList<>();
//        Random random = new Random();
//        for(int i = 0; i < 10000; i++) {
//            InventoryChangeRequest request = new InventoryChangeRequest();
//            request.setInventoryId(new InventoryId((long)random.nextInt(3),
//                    (long)random.nextInt(3),
//                    (long)random.nextInt(3),
//                    (long)1));
//            request.setOperationType(InventoryOperationTypeEnum.ADDITION);
//            request.setOperationId(1L);
//            request.setBizId(1L);
//            request.setCount(1);
//            requestList.add(request);
//        }
//        requestList.parallelStream().forEach((request -> inventoryService.updateInventory(request)));
//        return Result.ok();
//    }
//
//    @PostMapping("test/inventory/update-collection")
//    public Result<?> testUpdateInvertoryCollection() {
//        List<InventoryChangeRequest> requestList = new ArrayList<>();
//        Random random = new Random();
//        for(int i = 0; i < 10; i++) {
//            InventoryChangeRequest request = new InventoryChangeRequest();
//            request.setInventoryId(new InventoryId((long)random.nextInt(3),
//                    (long)random.nextInt(3),
//                    (long)random.nextInt(3),
//                    (long)1));
//            request.setOperationType(InventoryOperationTypeEnum.ADDITION);
//            request.setOperationId(1L);
//            request.setBizId(1L);
//            request.setCount(1);
//            requestList.add(request);
//        }
//        inventoryService.updateInventory(requestList);
//        return Result.ok();
//    }

}
