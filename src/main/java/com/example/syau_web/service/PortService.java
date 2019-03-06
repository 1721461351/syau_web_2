package com.example.syau_web.service;

import com.example.syau_web.domain.PortData;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

/**
 * create by wangyu
 * 2018/10/20 0020 21:13
 *
 * 串口的service
 */
public interface PortService {

    public PortData showPortData(PortData portData);

    /**
     * (自动)向数据库中增加串口中的数据
     * @return
     */
    PortData insertPortData();

    /**
     * (自动)删除一条数据
     * @return
     */
    PortData deleteOneData(Integer portId);

    /**
     * (自动)查询串口中传递过来的数据数据
     * @return
     */
    List<PortData> selectAll();

    /**
     * 自动查询最新的一条数据
     * @return
     */
    PortData findMaxId();
}
