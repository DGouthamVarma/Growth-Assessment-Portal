create table mentor(mentor_id varchar2(5) primary key, mentor_name varchar2(50), mentor_email varchar2(50), password varchar2(30) default 'mentor', gender varchar2(10), profilepic blob);
alter table mentor add mentor_role varchar2(50);

create sequence mentorid
start with 1
increment by 1
minvalue 1
maxvalue 999;

create or replace procedure insertmentordetails (mentorname in varchar2, mentoremail in varchar2, gender in varchar2, mentorrole in varchar2)
as
defaultstring varchar2(2):= 'TS';
newidnumber number:=mentorid.nextval;
newid varchar2(5);
begin
    if newidnumber < 10 then
        newid := defaultstring || '00' || to_char(newidnumber); 
    elsif newidnumber < 100 then
        newid := defaultstring || '0' || to_char(newidnumber);
    else 
        newid := defaultstring || to_char(newidnumber);
    end if;
    insert into mentor(mentor_id, mentor_name, mentor_email, gender, mentor_role) values(newid, mentorname, mentoremail, gender, mentorrole);
end;

/*-----------------------------------------------------------------------------------------------------*/


create table Team(Team_ID varchar2(7) primary key, TeamName varchar2(50), mentor_id varchar2(5) references mentor(mentor_id), dateofstart date, Teampic blob);
alter table team add flag number;

create sequence teamid
start with 1
increment by 1
minvalue 1
maxvalue 999;

create or replace procedure insertteamdetails(teamname in varchar2, mentorid in varchar2, dateofstart in varchar2, flag in number)
as
defaultstring varchar2(4):= 'TS_T';
newidnumber number:=teamid.nextval;
newid varchar2(7);
begin
    if newidnumber < 10 then
        newid := defaultstring || '00' || to_char(newidnumber); 
    elsif newidnumber < 100 then
        newid := defaultstring || '0' || to_char(newidnumber);
    else 
        newid := defaultstring || to_char(newidnumber);
    end if;
    insert into team(team_id, teamname, mentor_id, dateofstart, flag) values(newid, teamname, mentorid, to_date(dateofstart, 'YYYY-MM-DD'), flag);
end;

/*---------------------------------------------------------------------------------------------------------------------*/

create table trainee(trainee_id varchar2(7) primary key, trainee_name varchar2(50) not null, team_id varchar2(7) references team(team_id), trainee_email varchar2(50), password varchar2(50) default 'trainee', gender varchar2(10), dateofjoining date, profilepic blob);

create sequence traineeid 
start with 1
increment by 1
minvalue 1
maxvalue 999;

create or replace procedure inserttraineedetails(traineename in varchar2, teamid in varchar2, traineeemail in varchar2, dateOfJoining in varchar2, gender in varchar2)
as
defaultstring varchar2(4):= 'TS_S';
newid varchar2(7);
newidnumber number:=traineeid.nextval;
begin
    if newidnumber < 10 then
        newid := defaultstring || '00' || to_char(newidnumber); 
    elsif newidnumber < 100 then
        newid := defaultstring || '0' || to_char(newidnumber);
    else 
        newid := defaultstring || to_char(newidnumber);
    end if;
    insert into trainee(trainee_id, trainee_name, team_id, trainee_email, dateofjoining, gender) values(newid, traineename, teamid, traineeemail, TO_DATE(dateOfJoining,'YYYY-MM-DD') ,gender);
end;

/*---------------------------------------------------------------------------------------------------------*/

create table project(project_id varchar2(7) primary key, team_id varchar2(7) references team(team_id), trainee_id varchar2(7) references trainee(trainee_id),domain varchar2(50) not null, project_name varchar2(100) not null, project_type varchar2(10) not null, PRD blob, TDD blob);

create sequence projectid
start with 1
increment by 1
minvalue 1
maxvalue 999;

create or replace procedure insertprojectdetails(team_id in varchar2, trainee_id in varchar2, domain in varchar2, project_name in varchar2, project_type varchar2)
as
defaultstring varchar2(4):= 'TS_P';
newid varchar2(7);
newidnumber number:=projectid.nextval;
begin
    if newidnumber < 10 then
        newid := defaultstring || '00' || to_char(newidnumber); 
    elsif newidnumber < 100 then
        newid := defaultstring || '0' || to_char(newidnumber);
    else 
        newid := defaultstring || to_char(newidnumber);
    end if;
    insert into project(project_id, team_id, trainee_id, domain, project_name, project_type) values(newid, team_id, trainee_id, domain, project_name,project_type);
end;

/*-----------------------------------------------------------------------------------------*/

alter table project add trainee_id varchar2(7) not null;


alter table project drop column trainee_id;


/*-------------------------------------------------*/
create table traineerating(trainee_id varchar2(7) references trainee(trainee_id), 
dateofrating date, Week number, Logicalthinking number, codingstandards number, 
bitbucketusage number, othersperspective number, grammar number, verbalcommunication number,
understandingwritteninfo number, questioningability number, emailwriting number, dresscode number,
attendance number, timesheet number, teambonding number, assignments number, assessments number);


create or replace procedure inserttraineerating(traineeid in varchar2, dateofjoining in varchar2, week in number, logicalthinking in number, codingstandards in number, bitbucketusage in number,
othersperspective in number, grammar in number, verbalcommunication in number, understandingwritteninfo in number, questioningability in number, emailwriting in number,
dresscode in number,attendance in number, timesheet in number,teambonding in number,assignments in number, assessments in number)
as
begin
    insert into traineerating values (traineeid, TO_DATE(dateofjoining,'YYYY-MM-DD'), week,logicalthinking, codingstandards, bitbucketusage, othersperspective, grammar, verbalcommunication, understandingwritteninfo, questioningability, emailwriting, dresscode,attendance,timesheet,teambonding, assignments, assessments);
end;

/*-------------------------------------------------------------------------------------------*/

/*--------------------------------------------------------------------------------------------*/
create table technicalmentorrating(mentor_id varchar2(5) references mentor(mentor_id), trainee_id varchar2(7) references trainee(trainee_id), dateofrating date,
week number, dresscode number default 0, assignments number default 0, assessments number default 0);

create or replace procedure inserttechmentorrating(mentor_id in varchar2, trainee_id in varchar2, dateofrating in varchar2, week in number, dresscode in number, 
assignments in number, assessments in number)
as 
begin 
    insert into technicalmentorrating values(mentor_id, trainee_id, TO_DATE(dateofrating,'YYYY-MM-DD'),week, dresscode, assignments, assessments);
end;

create table centuryfluenciesmentorrating(mentor_id varchar2(5) references mentor(mentor_id), trainee_id varchar2(7) references trainee(trainee_id),
week number, othersperspective number, grammar number, verbalcommunication number, understandingwritteninfo number, questioningability number, emailwriting number);
drop table centuryfluenciesmentorrating;
truncate table centuryfluenciesmentorrating;
select * from centuryfluenciesmentorrating;
/*--------------------------------------------------------------------*/
create table assessment(mentor_id varchar2(5) references mentor(mentor_id), trainee_id varchar2(7) references trainee(trainee_id),
marks number default 0,week number not null, dateofassessment date, topic varchar2(50) not null);
desc assessment;
create or replace procedure insertassessmentdetails(mentor_id in varchar2, trainee_id in varchar2, marks in number, week in number, dateofassessment in varchar2,
topic in varchar2)
as
begin
    insert into assessment values(mentor_id, trainee_id, marks, week, TO_DATE(dateofassessment, 'YYYY-MM-DD'), topic);
end;


/*-----------------------------------------------------------------*/
create table projectreview(trainee_id varchar2(7) references trainee(trainee_id), mentor_id varchar2(5) references mentor(mentor_id), project_type varchar2(10), dateofreview date, comments varchar2(2000));

create or replace procedure insertprojectreview(trainee_id in varchar2, mentor_id in varchar2, project_type in varchar2, dateofreview in varchar2, comments  in varchar2)
as 
begin 
    insert into projectreview values(trainee_id, mentor_id, project_type, TO_DATE(dateofreview,'YYYY-MM-DD'),comments);
end;
