##Get started

- `git clone https://github.com/maximeGirma/boilerplate-java-spring-hibernate.git myApp`
- `cd myApp`
- `docker-compose build`
- `docker-compose up`

The stack is pretty slow, get a coffee while building.

##Troubleshooting

On windows an error can occurs when running the container

>Exited with code 127 

This error is caused by git so you need to edit your config. 

`git config --global core.autocrlf false`

Then pull again and it should work!