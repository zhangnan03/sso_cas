package cn.symdata.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BaseDao<T> extends PagingAndSortingRepository<T, String>,JpaSpecificationExecutor<T>{

}
