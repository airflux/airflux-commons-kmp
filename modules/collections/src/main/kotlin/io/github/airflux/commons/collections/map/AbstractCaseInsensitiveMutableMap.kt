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

package io.github.airflux.commons.collections.map

import io.github.airflux.commons.text.CaseInsensitiveString
import io.github.airflux.commons.text.caseInsensitive

public abstract class AbstractCaseInsensitiveMutableMap<T>(
    factory: () -> MutableMap<CaseInsensitiveString, T>
) : MutableMap<String, T> {

    protected val target: MutableMap<CaseInsensitiveString, T> = factory()

    override val size: Int
        get() = target.size

    override fun isEmpty(): Boolean = target.isEmpty()

    override fun containsKey(key: String): Boolean = target.containsKey(CaseInsensitiveString(key))

    override fun containsValue(value: T): Boolean = target.containsValue(value)

    override fun get(key: String): T? = target[key.caseInsensitive()]

    override fun put(key: String, value: T): T? = target.put(key.caseInsensitive(), value)

    override fun putAll(from: Map<out String, T>) {
        for ((key, value) in from) {
            put(key, value)
        }
    }

    override fun remove(key: String): T? = target.remove(key.caseInsensitive())

    override fun clear() {
        target.clear()
    }

    override val keys: MutableSet<String>
        get() = MutableSetProxy(
            target = target.keys,
            convertToExternal = { get },
            convertToInternal = { caseInsensitive() }
        )

    override val entries: MutableSet<MutableMap.MutableEntry<String, T>>
        get() = MutableSetProxy(
            target = target.entries,
            convertToExternal = { Entry(key.get, value) },
            convertToInternal = { Entry(key.caseInsensitive(), value) }
        )

    override val values: MutableCollection<T> get() = target.values

    internal class Entry<K : Any, T>(
        override val key: K,
        override var value: T
    ) : MutableMap.MutableEntry<K, T> {

        override fun setValue(newValue: T): T {
            val previous = value
            value = newValue
            return previous
        }

        override fun hashCode(): Int = key.hashCode()

        override fun equals(other: Any?): Boolean = when {
            this === other -> true
            other is Map.Entry<*, *> -> key == other.key
            else -> false
        }

        override fun toString(): String = "$key=$value"
    }
}
