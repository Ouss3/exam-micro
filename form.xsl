<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text" />

    <xsl:template match="/">
        import { Component } from '@angular/core';
        import {CommonModule} from "@angular/common";
        import {FullCalendarModule} from "@fullcalendar/angular";
        import {CalendarOptions} from "@fullcalendar/core";
        import timeGridPlugin from '@fullcalendar/timegrid';
        import dayGridPlugin from '@fullcalendar/daygrid';

        @Component({
        selector: 'app-calendar',
        standalone: true,
        imports: [ FullCalendarModule, CommonModule],
        templateUrl: './calendar.component.html',
        styleUrl: './calendar.component.css'
        })
        export class CalendarComponent {
        calendarOptions: CalendarOptions = {
        initialView: 'timeGridWeek',
        headerToolbar: { // Customize the header toolbar
        left: 'prev,next today', // Buttons to be displayed on the left
        center: 'title', // Title to be displayed in the center
        right: 'dayGridMonth,timeGridWeek,timeGridDay' // Buttons to be displayed on the right
        },
        businessHours: { // Specify business hours
        daysOfWeek: [ 1, 2, 3, 4, 5 ], // Monday - Friday
        startTime: '08:00', // Start time
        endTime: '18:00', // End time
        },
        slotMinTime: '08:00:00', // Start time slots at 8 AM
        slotMaxTime: '18:00:00', // End time slots at 8 PM
        slotDuration: '00:10:00', // Display time by minutes
        plugins:[timeGridPlugin,dayGridPlugin],
        //dateClick: this.handleDateClick.bind(this),
        events: [

        ]
        };

        // Array of student IDs
        students:Array&lt;any&gt; =[
        <xsl:for-each select="formSubmit/form">
            <xsl:sort select="theme" data-type="text" order="ascending"/>
        {
        firstname: '<xsl:value-of select="firstAuthor/firstname" />',
        lastname: '<xsl:value-of select="firstAuthor/lastName" />',
        theme: '<xsl:value-of select="theme" />'

        },
    </xsl:for-each>


        ] ;
        colors: string[] = ['red', 'blue', 'green', 'yellow', 'purple'];

        themes: string[] = [...new Set(this.students.map(student => student.theme))];
        // Split students into chunks of 12
        days : number[][]= [];
        schedule : any = [];
        themeColors: { [key: string]: string } = {};
        colorRooms: { [key: string]: string } = {};
        constructor() {
        // Assign each theme a color
        this.themes.forEach((theme, index) => {
        this.themeColors[theme] = this.colors[index % this.colors.length];

        });

        this.colors.forEach((color, index) => {
        this.colorRooms[color] = 'Room ' + (index + 1);
        });

        while (this.students.length) {
        this.days.push(this.students.splice(0, 12));
        }

        this.schedule = this.days.map((day, i) => {
        let date = new Date();
        date.setDate(date.getDate() + i);
        let morningStart = new Date(date.getTime());
        morningStart.setHours(9, 0, 0, 0);

        let afternoonStart = new Date(date.getTime());
        afternoonStart.setHours(14, 0, 0, 0);

        let appointments = [];

        appointments.push(...this.scheduleAppointments(day.slice(0, 6), morningStart));
        appointments.push(...this.scheduleAppointments(day.slice(6, 12), afternoonStart));

        return appointments;
        });

        this.calendarOptions.events = this.getEventsFromSchedule();
        }
        scheduleAppointments(students: number[], start: Date): { student: any, start: Date ,end :Date}[] {
        let appointments = [];
        let date = new Date(start.getTime());

        for (let i = 0; i &lt; students.length; i++) {
        let appointment = { student: students[i], start: new Date(date.getTime()), end : new Date(date.getTime() + 20 * 60 * 1000) };
        appointments.push(appointment);

        if ((i + 1) % 3 === 0) {
        date.setMinutes(date.getMinutes() + 35); // Add 20 minutes for the appointment and 15 minutes for the break
        } else {
        date.setMinutes(date.getMinutes() + 20); // Add 20 minutes for the appointment
        }
        }

        return appointments;
        }
        getEventsFromSchedule(): { title: string, start: Date ,color :string }[] {
        // Flatten the schedule array and map each appointment to an event object
        return this.schedule.flat().map((appointment : { student: any, start: Date ,end:Date })=> ({
        title: `${appointment.student.firstname} ${appointment.student.lastname}`,
        start: appointment.start,
        end: appointment.end,
        color: this.themeColors[appointment.student.theme]
        }));
        }
        }

    </xsl:template>
</xsl:stylesheet>