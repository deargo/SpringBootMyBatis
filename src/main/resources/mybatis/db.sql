
CREATE OR REPLACE procedure P_TEST_PAGING_QUERY
(
    p_pagesql   in varchar2,     --sql
    p_curPage     in out Number ,  --当前页
    p_pageSize    in out Number ,  --每页显示记录的条数
    p_totalRecords out Number,    --总记录数
    p_totalPages out Number  ,    -- 总页数 
    pageResultSet out  SYS_REFCURSOR              -- 输出结果集游标
)
as
  v_sql       varchar2(2000):='';  --sql语句
  v_startRecord Number;         --开始显示的记录数
  v_endRecord   Number;         --结束显示的记录条数

begin
   --记录总记录条数       
               v_sql:='select count(*) FROM (' || p_pagesql || ')';
               execute IMMEDIATE v_sql INTO p_totalRecords;
 
                        IF MOD(p_totalRecords,p_pageSize)=0 THEN
                          --得到整数则直接取得到的页码数否在原来的基础上增加一个页码
                          p_totalPages:=p_totalRecords/p_pageSize;
                        ELSE
                          p_totalPages:=p_totalRecords/p_pageSize+1;
                        END IF;
                       
                        --验证页号
                        IF p_curPage<1 THEN
                          p_curPage:=1;
                        END IF;
                       
                        --如果取的当前页大于总页数则取最大页数的数据
                        IF p_curPage>p_totalPages THEN
                          p_curPage:=p_totalPages;
                        END IF;
                       --实现分页查询
                        v_startRecord :=(p_curPage - 1) * p_pageSize + 1;
                        v_endRecord   :=p_curPage * p_pageSize;	
                        v_sql           := 'select * from (SELECT t.*, ROWNUM RN from (' || p_pagesql || ') t where rownum<=' || v_endRecord || ' ) where RN>=' ||v_startRecord;						
                        p_totalPages:=floor(p_totalPages);  --去整数总页
                        OPEN pageResultSet FOR v_sql;
             exception
                when others then
                     CLOSE pageResultSet;					

end P_TEST_PAGING_QUERY;
/



 