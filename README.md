## Get started

- `git clone https://github.com/maximeGirma/asilum.git asilum`
- `cd asilum`
- `docker-compose build`
- `docker-compose up`

The stack is pretty slow, get a coffee while building (seriously).

When the line 
`2019-09-10 10:27:24.625  INFO 68 --- [           main] asilum.Main                              : Started Main in 16.436 seconds (JVM running for 17.586)` 
is displayed in your terminal, the stack is ready.

Enter the following URL to ensure the stack is responding properly:
`http://localhost:8090/greeting`
If the system works you should see an hello world in your navigator.



## Customize the app

If you want to change the db's name, the app's name or the used port you can change it in .env file.


You can also change password and user. 


You need to delete the database folder and rebuild the stack to apply these changes.


## Troubleshooting

On windows an error can occurs when running the container

>Exited with code 127 

This error is caused by git so you need to edit your config. 

`git config --global core.autocrlf false`

Then pull again and it should work!