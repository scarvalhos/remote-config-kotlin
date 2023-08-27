package com.sam.remoteconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RemoteconfigApplication

fun main(args: Array<String>) {
	runApplication<RemoteconfigApplication>(*args)
}
