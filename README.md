## Get started

- `git clone https://github.com/maximeGirma/asilum.git`
- `cd myApp`
- `docker-compose build`
- `docker-compose up`

The stack is pretty slow, get a coffee while building.

## Customize the app

If you want to change the db or the app name you can change it in .env file.


You can also change password and user. 


You need to delete the database folder and rebuild the stack to apply these changes.


## Troubleshooting

On windows an error can occurs when running the container

>Exited with code 127 

This error is caused by git so you need to edit your config. 

`git config --global core.autocrlf false`

Then pull again and it should work!