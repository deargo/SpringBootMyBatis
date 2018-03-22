package com.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;


//映射Sql，定义接口
public interface DataMapper {	

	//查询。@Param对应参数属性注解，There is no getter for property named 'xx' in 'class java.lang.Integer
	List<Employee> test_query(@Param("EMPNO")Integer EMPNO);

	//插入
	void test_insert(Employee employee);

	//更新
	void test_update(@Param("EMPNO")Integer EMPNO, @Param("COMM")double COMM);

	//删除
	void test_delete(Integer EMPNO);

	//批量插入
	void test_multi_insert(List<Employee> results);

	//批量查询
	List<Employee> test_multi_query(int[] DEPTNOArr);

	//批量删除
	void test_multi_delete(List<Integer> EMPNOList);

	//存储过程
	void test_exe_procedure1(Map<String, Object> params);
	void test_exe_procedure2(Map<String, Object> params);
}
