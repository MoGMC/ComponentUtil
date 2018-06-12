package moe.hilaryoi.mc;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class ComponentUtil {

	public static BaseComponent[] parseMarkup (String markup) {

		ComponentBuilder b = new ComponentBuilder ("");

		Parser p = new Parser (markup);

		while (!p.isFinished ()) {

			p.continueUntil ('(');

			String message = p.scrape ();

			if (!message.equals ("")) {

				b.append (color (message));

			}

			p.continueUntil (')');

			String[] command = p.scrape ().split (" ", 2);

			if (command.length < 2) {

				if (command[0].equals ("end")) break;
				if (command[0].equals ("r")) b.reset ();

				continue;

			}

			switch (command[0]) {

				case "c":
					b.color (ChatColor.getByChar (command[1].charAt (0)));
					break;
				case "t":
					b.event (new HoverEvent (HoverEvent.Action.SHOW_TEXT, new ComponentBuilder (color (command[1])).create ()));
					break;
				case "l":
					b.event (new ClickEvent (ClickEvent.Action.OPEN_URL, command[1]));
					break;
				case "cmd":
					b.event (new ClickEvent (ClickEvent.Action.RUN_COMMAND, command[1]));

			}

		}

		return b.create ();

	}

	public static String color (String text) {

		return ChatColor.translateAlternateColorCodes ('&', text);

	}

}
