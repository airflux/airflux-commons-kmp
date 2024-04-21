/*
 * Copyright 2024 Maxim Sambulat.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.airflux.commons.types.result

import io.github.airflux.commons.types.RaiseException
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class ResultRaiseTest : FreeSpec() {

    init {

        "The Result#Raise type" - {

            "the `component1` function" - {

                "when the result is successful" - {
                    val raise = Result.Raise<Error>()
                    val result: Result<Int, Error> = FIRST_VALUE.success()

                    "then should return the value" {
                        with(raise) {
                            val (bindValue: Int) = result
                            bindValue shouldBe FIRST_VALUE
                        }
                    }
                }

                "when the result is failure" - {
                    val raise = Result.Raise<Error>()
                    val result: Result<Int, Error> = Error.First.failure()

                    "then should raise an exception" {
                        val exception = shouldThrow<RaiseException> {
                            with(raise) {
                                val (bindValue) = result
                                bindValue
                            }
                        }

                        exception.raise shouldBe raise
                        exception.failure shouldBe result
                    }
                }
            }

            "the `bind` function" - {

                "when the result is successful" - {
                    val raise = Result.Raise<Error>()
                    val result: Result<Int, Error> = FIRST_VALUE.success()

                    "then should return the value" {
                        with(raise) {
                            val bindValue: Int = result.bind()
                            bindValue shouldBe FIRST_VALUE
                        }
                    }
                }

                "when the result is failure" - {
                    val raise = Result.Raise<Error>()
                    val result: Result<Int, Error> = Error.First.failure()

                    "then should raise an exception" {
                        val exception = shouldThrow<RaiseException> {
                            with(raise) {
                                result.bind()
                            }
                        }

                        exception.raise shouldBe raise
                        exception.failure shouldBe result
                    }
                }
            }

            "the `raise` function" - {

                "when the result is successful" - {
                    val raise = Result.Raise<Error>()
                    val result: Result<Int, Error> = FIRST_VALUE.success()

                    "then should not raise an exception" {
                        with(raise) {
                            result.raise()
                        }
                    }
                }

                "when the result is failure" - {
                    val raise = Result.Raise<Error>()
                    val result: Result<Int, Error> = Error.First.failure()

                    "then should raise an exception" {
                        val exception = shouldThrow<RaiseException> {
                            with(raise) {
                                result.raise()
                            }
                        }

                        exception.raise shouldBe raise
                        exception.failure shouldBe result
                    }
                }
            }

            "the `ensure` function" - {

                "when condition is true" - {
                    val condition = true

                    "then should return a successful value" {
                        val result: Result<Unit, Error> = Result {
                            ensure(condition) { Error.First }
                        }

                        result shouldBeSuccess Unit
                    }
                }

                "when condition is false" - {
                    val condition = false

                    "then should return a failure value" {
                        val result: Result<Unit, Error> = Result {
                            ensure(condition) { Error.First }
                        }

                        result shouldBeFailure Error.First
                    }
                }
            }

            "the `ensureNotNull` function" - {

                "when value is not null" - {
                    val value: Int? = createValue(FIRST_VALUE)

                    "then should return a successful value" {
                        val result: Result<Int, Error> = Result {
                            ensureNotNull(value) { Error.First }
                        }

                        result shouldBeSuccess value
                    }
                }

                "when value is null" - {
                    val value: Int? = createValue(null)

                    "then should return a failure value" {
                        val result: Result<Int, Error> = Result {
                            ensureNotNull(value) { Error.First }
                        }

                        result shouldBeFailure Error.First
                    }
                }
            }
        }
    }

    internal sealed class Error {
        data object First : Error()
    }

    private companion object {
        private const val FIRST_VALUE = 1
    }

    private fun createValue(value: Int?): Int? = value
}
