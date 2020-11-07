package h577870.entity

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import kotlin.reflect.full.companionObject

/**
 * @author Kristoffer Davidsen
 * @param brukerid -> Oppgave tilhører bruker med id
 * @param tittel -> Tittel på oppgaven, genereres automatico.
 * @param beskrivelse -> Beskrivelse av oppgaven.
 * @param type -> Definerer hvilken type oppgave det er.
 * @param vareliste -> Inneholder liste over vareobjekter som det skal gjøres endringer på.
 * @param status -> Definerer oppgavens nåværende status.
 *
 */

@ExperimentalSerializationApi
@Serializable
data class OppgaveClass(
        val brukerid: String,
        val tittel: String,
        val beskrivelse: String,
        val vareliste: List<VareClass>,
        @Serializable(with = OppgaveTypeSerializer::class)
        val type: Enum<OppgaveType>,
        @Serializable(with = OppgaveStatusSerializer::class)
        val status: Enum<OppgaveStatus>,

)

@ExperimentalSerializationApi
object Oppgave : Table() {
        val brukerid: Column<String> = varchar("brukerid", length = 50)
                .references(Bruker.brukernavn)
        val tittel: Column<String> = varchar("tittel", length = 50)
        val beskrivelse: Column<String> = varchar("beskrivelse", length = 200)
        /*
        TODO: Kserializer for vareliste
         */
        val vareliste: Column<String> = varchar("vareliste", length = Int.MAX_VALUE)
        val type: Column<String> = varchar("type", 20)
        val status: Column<String> = varchar("status", 20)
}