# Project_IT_DA<br>
깃 배쉬 사용법<br>
자기가 작업할때 사용하는 워크스페이스 폴더 -> 프로젝트 이름 -> src -> main 으로 이동<br>
폴더에서 webapp이 보이는 상태에서 마우스 오른쪽 클릭 git bash 실행<br>
<br>
깃 배쉬 창에서 순서대로 명령어 실행<br>  
@처음 연결하는 경우@<br>
git remote add a https://github.com/TeamL3Project/Project_IT_DA.git    <br>
   깃 원격 저장소 연결 (a는 임의의 값, 기억만 하면 마음대로 해도 상관없음)<br>
git pull a main                                                    
   main 브랜치의 자료들을 pull해옴<br>
git branch 브랜치이름                                               
   자신이 앞으로 쓸 브랜치 이름 지정, 생성 (처음 설정하고 나면 배쉬가 계속 기억함)<br>
<br><br>

*fatal: refusing to merge unrelated histories 가 떴을때<br>
git pull [설정한이름] [브랜치이름] --allow-unrelated-histories      
    입력       <br>
<br><br>
git pull [리모트이름] main 입력 후 <br>
error: Your local changes to the following files would be overwritten by merge:  에러 발생시
git stash 입력 후 <br> 다시 git pull [리모트이름] main
<br><br>

평소 사용할때<br>
git checkout 브랜치이름                                            
   자신의 브랜치로 이동<br>
git add *
    자신이 작업한(변경한) 파일들을 스테이지에 올림<br>
git commit -m "커밋 메세지"
    커밋 메세지를 꼭! 작성하고 스테이지에 올린 파일들을 커밋해줌<br>
git push a 브랜치이름
    커밋한 파일들을 브랜치에 push함<br>
<br><br>

나머지 안되는건 카톡으로~<br>

