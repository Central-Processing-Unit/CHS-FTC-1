# 2021-2022 CHS Engineering (Delta) repository

Since this repo isn't public I'm gonna just put shit for Sam and Harrison here

Here's roughly how I wanna organize our classes:
[organization.pdf](https://github.com/gt74745/CHS_Delta_2022/files/7107820/organization.pdf)

## WIP list of methods we want to have for our highest level auton and teleOp classes:
- **init()** : Every motor, servo, and sensor needs to be defined and given an instance in order for us to use them as part of an external object (i.e. Base.java)
- **move(x, y, angle)** : Needs to fully support the x-drive system and be able to pipe x and y inputs into a PID controller
- **doShit()** : Probably not a final method name, but we need to be able to easily do whatever the game calls for with a single method

Add more stuff here if you want to
