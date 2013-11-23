--Oracle
CREATE OR REPLACE FUNCTION NEWID RETURN VARCHAR2 AS
GUID varchar(64);
begin
  GUID := SYS_GUID();
  RETURN
  substr(GUID,1,8)||'-'||substr(GUID,9,4)||
  '-'||substr(GUID,13,4)||'-'||substr(GUID,17,4)
  ||'-'||substr(GUID,21,12);
end NEWID;
/
