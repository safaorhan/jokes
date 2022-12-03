package com.safaorhan.jokes.api

import java.util.concurrent.*
import java.util.stream.Collectors

/**
 * Based of https://gist.github.com/vladimir-bukhtoyarov/38d6b4b277d0a0cfb3af
 *
 * This makes okhttp do synchronous processing. Should be only used for unit tests.
 */
class CurrentThreadExecutor : ExecutorService {
    override fun execute(command: Runnable) = command.run()

    override fun shutdown() {}

    override fun shutdownNow(): MutableList<Runnable> = mutableListOf()

    override fun isShutdown() = false

    override fun isTerminated() = false

    override fun awaitTermination(timeout: Long, unit: TimeUnit?) = false

    override fun <T> submit(task: Callable<T>?): Future<T> {
        val futureTask = FutureTask(task)
        futureTask.run()
        return futureTask
    }

    override fun <T> submit(task: Runnable, result: T): Future<T> {
        val futureTask = FutureTask<T>(task, result)
        futureTask.run()
        return futureTask
    }

    override fun submit(task: Runnable): Future<*> {
        val futureTask = FutureTask(task, null)
        futureTask.run()
        return futureTask
    }

    override fun <T> invokeAll(tasks: MutableCollection<out Callable<T>>?) =
        tasks?.stream()?.map { submit(it) }?.collect(Collectors.toList())

    override fun <T> invokeAll(
        tasks: MutableCollection<out Callable<T>>?,
        timeout: Long,
        unit: TimeUnit?
    ) = invokeAll(tasks)

    override fun <T : Any?> invokeAny(tasks: MutableCollection<out Callable<T>>?) =
        tasks?.stream()?.map { submit(it) }?.findFirst()?.get()?.get()

    override fun <T : Any?> invokeAny(
        tasks: MutableCollection<out Callable<T>>?,
        timeout: Long,
        unit: TimeUnit?
    ) = invokeAny(tasks)
}

