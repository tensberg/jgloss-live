JGloss Live
===========

Web-based version of [JGloss](http://jgloss.sf.net/).

<a href="http://jgloss.cloudwatch.net/">Go To JGloss Live</a>

[![Build Status](https://travis-ci.org/tensberg/jgloss-live.svg?branch=master)](https://travis-ci.org/tensberg/jgloss-live)

Toy/Demo application. Proof of concept for various technologies. 

Software
--------

* [Gradle](http://gradle.org/)-based Java 8 build.
* Uses the [JGloss libraries](https://github.com/tensberg/jgloss-mirror) as backend for dictionary lookups and text glosses.
* [Spring Boot](http://projects.spring.io/spring-boot/) middleware for REST interface.
* [AngularJS](https://angularjs.org/)-based web application frontend.

Continuous Deployment
---------------------

1. git repository hosted on GitHub.
2. git push triggers build on [Travis CI](https://travis-ci.org/tensberg/jgloss-live).
3. CI build runs compile and tests.
4. Successful CI build creates Docker image and pushes it to [Docker Hub](https://hub.docker.com/r/tensberg/jgloss-live/).
5. Push to Docker Hub triggers the redeployment via [Tutum](https://www.tutum.co/).
6. Tutum redeploys the latest docker image to a server hosted by [Digital Ocean](http://www.digitalocean.com/?refcode=dd0a97e9e286).
7. <a href="http://jgloss.cloudwatch.net/">New version goes live</a>.

(C) 2015 Michael Koch <tensberg@gmx.net>

[EDICT dictionary](http://www.edrdg.org/jmdict/edict.html) (C) 2015 The Electronic Dictionary Research and Development Group