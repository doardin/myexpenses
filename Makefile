.PHONY: start
start:
	docker compose up -d

.PHONY: restart
restart:
	docker compose restart

.PHONY: stop
stop:
	docker compose down